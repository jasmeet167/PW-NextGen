import sys
import time

#############################################
# Setup global variables
#############################################

global AdminConfig
global AdminControl
global AdminApp

#############################################
# Get commmand line arguments
#############################################
argvCount = len(sys.argv)
earFile = sys.argv[argvCount-15]
warFile1 = sys.argv[argvCount-14]
warFile2 = sys.argv[argvCount-13]
webModule1 = sys.argv[argvCount-12]
webModule2 = sys.argv[argvCount-11]
nodeName = sys.argv[argvCount-10]
serverName = sys.argv[argvCount-9]
webServerNodeName = sys.argv[argvCount-8]
webServerName = sys.argv[argvCount-7]
appName = sys.argv[argvCount-6]
virtualHostName = sys.argv[argvCount-5]
compileJSPsOption = sys.argv[argvCount-4]
installConfigScript = sys.argv[argvCount-3]
postInstallConfigScript = sys.argv[argvCount-2]
serverAction = sys.argv[argvCount-1]

#############################################
# Main program starts hear
#############################################
 
#############################################
# Initialize configuration parameters
#############################################
 
# Cell and node
cellName = AdminControl.getCell( )
node = AdminConfig.getid("/Node:"+nodeName+"/" )
# Virtual host mapping - may be overridden
webModuleUri1 = warFile1
webModuleUri1 += ",WEB-INF/web.xml"
webModuleUri2 = warFile2
webModuleUri2 += ",WEB-INF/web.xml"
webModToVHMap = [[webModule1, webModuleUri1, virtualHostName],[webModule2, webModuleUri2, virtualHostName]]
# Server mapping - may be overridden
webServer = "WebSphere:cell="+cellName+",node="+webServerNodeName+",server="+webServerName
server = "WebSphere:cell="+cellName+",node="+nodeName+",server="+serverName
webModToServersMap = [[webModule1, webModuleUri1, server],[webModule2, webModuleUri2, server]]
# Empty shared library mapping - may be appended
webModulesToSharedLibMap = []
# Empty EJB deploy mapping - may be appended
jndiForEJBNonMessageBinding = []
EJBREfToEJBMap = []
# App install options may be appended in config script if specified
appOptions = []

#############################################
# Build and display install configuration
#############################################

print ""
if (cmp(installConfigScript, "NONE") == 0):
    # No install config script specified
    print "appName:              " + appName
    if (cmp(webServerName, "NONE") == 0):
        # No web server name specified - no server mapping needed just include node and server in options
        # Default server mapping
        appOptions += ["-MapWebModToVH", webModToVHMap, "-node", nodeName, "-server", serverName, "-appname", appName, "-"+compileJSPsOption]
    else:
        # Web server name specified so create server mapping to include web server
        webModToServersMap = [[webModule1, webModuleUri1, webServer+"+"+server],[webModule2, webModuleUri2, webServer+"+"+server]]
        print "webModToServersMap:  ",
        firstLine = -1
        for item in webModToServersMap:
            if (firstLine == 0) : print "                      ",
            print item
            firstLine = 0    
        #endIf 
        appOptions += ["-MapWebModToVH", webModToVHMap, "-MapModulesToServers", webModToServersMap, "-appname", appName, "-"+compileJSPsOption]
    #endElse 
else:
    # include install config script
    print "Install script:       " + installConfigScript
    print "appName:              " + appName
    execfile(installConfigScript )
    # Assumes default server mapping or overridden in install config script
    print "webModToServersMap:  ",
    firstLine = -1
    for item in webModToServersMap:
        if (firstLine == 0) : print "                      ",
        print item
        firstLine = 0    
    #endIf 
    # Assumes default virtual host mapping or overridden in install config script
    # Shared libraries already appended and displayed in install config script if specified
    # EJB Deploy options already appended and displayed in install config script if specified
    appOptions += ["-MapWebModToVH", webModToVHMap, "-node", nodeName, "-server", serverName, "-appname", appName, "-"+compileJSPsOption]
    # appOptions += ["-MapWebModToVH", webModToVHMap, "-MapModulesToServers", webModToServersMap, "-appname", appName, "-"+compileJSPsOption]
    # puts "$appOptions"
#endElse 
print "webModToVHMap:       ",
firstLine = -1
for item in webModToVHMap:
    if (firstLine == 0) : print "                     ",
    print item
    firstLine = 0
#endIf 
print "nodeName:             " + nodeName
print "serverName:           " + serverName,
        
#############################################
# Check for existance of node
#############################################

if (len(node) == 0):
    raise Exception("Error -- node " + nodeName + " not found")
#endIf 
nodeSync = AdminControl.completeObjectName("type=NodeSync,node="+nodeName+",*" )
if (len(nodeSync) == 0):
   raise Exception("Error -- NodeSync MBean not found for name " + nodeName)
#endIf 

##################################################
# Assume server exists no node - stop if running
##################################################

# print "Checking to see if server "+serverName+" is running on node "+nodeName+"..."
runningServer = AdminControl.completeObjectName("type=Server,node=" + nodeName + ",process=" + serverName + ",*")
if (len(runningServer) > 0):
    print "(running)"
    print ""
    print "Stopping server..." + serverName + " on node " + nodeName + "..."
    AdminControl.stopServer(serverName, nodeName )
else:
    print "(stopped)"
#endElse 
print ""

##################################################
# Uninstall application if exists
##################################################

application = AdminConfig.getid("/Deployment:"+appName+"/" )
if (len(application) > 0):
    print "Uninstalling application..."
    AdminApp.uninstall(appName)
    print ""
#endIf
             
##################################################
# Install application
##################################################

print "Installing application..."
earFile = earFile.replace("\\", "/")
AdminApp.install(earFile, appOptions )
print ""

##################################################
# Run post install script if sepecified
##################################################

if (postInstallConfigScript != "NONE"):
    deployment = AdminConfig.getid("/Deployment:"+appName+"/" )
    appDeployed = AdminConfig.showAttribute(deployment, "deployedObject" )
    print "Post install script:  " + postInstallConfigScript
    execfile(postInstallConfigScript )
#endIf 

##################################################
# Save changes and sync node
##################################################

print "Saving configuration data..."
AdminConfig.save( )
enabled = AdminControl.getAttribute(nodeSync, "serverStartupSyncEnabled" )
if (cmp(enabled, "false") == 0):
    print "Synchronizing node " + nodeName + "..."
    AdminControl.invoke(nodeSync, "sync" )
#endIf 
print ""

##################################################
# Wait for application distribution to complete
##################################################

print "Waiting for application distribution..." 
readyValue = "false"	
while readyValue == 'false':
    time.sleep(1)
    readyValue = AdminApp.isAppReady(appName)
    continue
if readyValue == "true":
    print "Application distrubution complete"
else:
    print "There was a problem with the distrubution of the " + appName + " application. " + nl + " Please review logs and output messages."
#end if
print ""

##################################################
# Start server
##################################################

if (cmp(serverAction, "START") == 0):
    print "Starting server " + serverName + " on node " + nodeName + "..."
    AdminControl.startServer(serverName, nodeName, 720)
    print ""
#end if

##################################################
# Install complete
##################################################

print "Application " + appName + " installed and started"
print ""