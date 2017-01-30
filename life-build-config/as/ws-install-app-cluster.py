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
ScriptLocation = sys.argv[argvCount-13]
earFile = sys.argv[argvCount-12]
warFile = sys.argv[argvCount-11]
webModule = sys.argv[argvCount-10]
clusterName = sys.argv[argvCount-9]
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
ScriptLocation = ScriptLocation.replace("\\", "/")
execfile(ScriptLocation+"/NODES-pairs-unique.py" )
# Cell and node
cellName = AdminControl.getCell( )
node = AdminConfig.getid("/Node:"+nodeName+"/" )
# Virtual host mapping - may be overridden
webModuleUri = warFile
webModuleUri += ",WEB-INF/web.xml"
webModToVHMap = [[webModule, webModuleUri, virtualHostName]]
# Server mapping - may be overridden
webServer = "WebSphere:cell="+cellName+",node="+webServerNodeName+",server="+webServerName
# Cluster
runningClusterMgr = AdminControl.completeObjectName("type=ClusterMgr,cell="+cellName+",*" )
cluster_id = AdminConfig.getid("/ServerCluster:"+clusterName+"/" )
cluster = "WebSphere:cell="+cellName+",cluster="+clusterName
runningCluster = AdminControl.completeObjectName("type=Cluster,cell="+cellName+",name="+clusterName+",*" )
nodeServerPairs = getClusterNodeServerPairs(cluster_id)
nodesContainedServers = determineUniqueNodesContainedServers(nodeServerPairs )
# Web modules mapping - may be overridden
webModToServersMap = [[webModule, webModuleUri, cluster]]
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
        appOptions += ["-MapWebModToVH", webModToVHMap, "-cluster", clusterName, "-appname", appName, "-"+compileJSPsOption]
    else:
        # Web server name specified so create server mapping to include web server
        webModToServersMap = [[webModule, webModuleUri, webServer+"+"+cluster]]
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
    # appOptions += ["-MapWebModToVH", webModToVHMap, "-cluster", clusterName, "-appname", appName, "-"+compileJSPsOption]
    appOptions += ["-MapWebModToVH", webModToVHMap, "-MapModulesToServers", webModToServersMap, "-appname", appName, "-"+compileJSPsOption]
    # puts "$appOptions"
#endElse 
print "webModToVHMap:       ",
firstLine = -1
for item in webModToVHMap:
    if (firstLine == 0) : print "                     ",
    print item
    firstLine = 0
#endIf 
state = AdminControl.getAttribute(runningCluster, "state")
print "clusterName:          " + clusterName + " (state: " + state + ")"
print ""

#############################################
# Stop cluster if running
#############################################

if state != "websphere.cluster.partial.stop" and state != "websphere.cluster.stopped":
    print "Stopping cluster " + clusterName + "..."
    AdminControl.invoke(runningCluster, "stop")
    state = AdminControl.getAttribute(runningCluster, "state")
    while state != "websphere.cluster.stopped":
        time.sleep(1)
        state = AdminControl.getAttribute(runningCluster, "state")
    #endWhile
    print "Cluster state is " + state
    print ""
#endElse 

#############################################
# Is a nodeSync MBean available for each node in the cluster? 
#############################################

nodeNodeSyncPairs = []
for nodeServerPair in nodesContainedServers:
   nodeName = nodeServerPair[0]
   # print "Checking for the existence of a NodeSync MBean on node " + nodeName
   nodeSync = AdminControl.completeObjectName("type=NodeSync,node=" + nodeName + ",*")
   if len(nodeSync) == 0:
      print "Error -- NodeSync MBean not found for name " + nodeName
      sys.exit()
   #endIf
   nodeNodeSyncPair = [nodeName, nodeSync]
   nodeNodeSyncPairs.append(nodeNodeSyncPair)
#endFor
      
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
# Save changes and sync nodes
##################################################

print "Saving configuration data..."
AdminConfig.save( )
for nodeNodeSyncPair in nodeNodeSyncPairs:
    nodeName = nodeNodeSyncPair[0]
    nodeSync = nodeNodeSyncPair[1]
    enabled = AdminControl.getAttribute(nodeSync, "serverStartupSyncEnabled")
    if enabled == "false":
         print "Synchronizing node " + nodeName + "..."
         AdminControl.invoke(nodeSync, "sync")
    #endIf
#endFor
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
# Start cluster
##################################################

if (cmp(serverAction, "START") == 0):
    runningCluster = AdminControl.completeObjectName("type=Cluster,cell="+cellName+",name="+clusterName+",*" )
    state = AdminControl.getAttribute(runningCluster, "state")
    if state == "websphere.cluster.stopped":
        print "Starting cluster " + clusterName + "..."
        AdminControl.invoke(runningCluster, "start")
        state = AdminControl.getAttribute(runningCluster, "state")
        while state != "websphere.cluster.running" and state != "websphere.cluster.stopped":
            time.sleep(1)
            state = AdminControl.getAttribute(runningCluster, "state")
        #endWhile
        print "Cluster state is " + state
        print ""
    #endIf 
#end if

##################################################
# Install complete
##################################################

print "Application " + appName + " installed and started"
print ""