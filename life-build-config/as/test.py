import sys

argvCount = len(sys.argv)
earFile = sys.argv[argvCount-13]
warFile = sys.argv[argvCount-12]
webModule = sys.argv[argvCount-11]
nodeName = sys.argv[argvCount-10]
serverName = sys.argv[argvCount-9]
webServerNodeName = sys.argv[argvCount-8]
webServerName = sys.argv[argvCount-7]
appName = sys.argv[argvCount-6]
virtualHostName = sys.argv[argvCount-5]
version = sys.argv[argvCount-4]
compileJSPsOption = sys.argv[argvCount-3]
installConfigScript = sys.argv[argvCount-2]
postInstallConfigScript = sys.argv[argvCount-1]

cellName = "mycell"
webModuleUri = warFile
webModuleUri += ",WEB-INF/web.xml"
webModToVHMap = [[webModule, webModuleUri, virtualHostName],["xx", webModuleUri, virtualHostName]]
# Server mapping - may be overridden
webServer = "Websphere:cell="+cellName+",node="+webServerNodeName+",server="+webServerName
server = "Websphere:cell="+cellName+",node="+nodeName+",server="+serverName
webModulesToServersMap = [[webModule, webModuleUri, server]]
# Empty shared library mapping - may be appended
webModulesToSharedLibMap = []
# Empty EJB deploy mapping - may be appended
jndiForEJBNonMessageBinding = []
EJBREfToEJBMap = []
# App install options may be appended in jacl config if specified
appOptioins = []

if (cmp(installConfigScript, "NONE") == 0):
    # No install config script specified
    print "appName:             " + appName
    print "nodeName:            " + nodeName
    print "serverName:          " + serverName
    if (cmp(webServerName, "NONE") == 0):
        # No web server name specified - no server mapping needed just include node and server in options
        appOptioins += ["-MapWebModToVH", webModToVHMap, "-node", nodeName, "-server", serverName, "-appname", appName, "-"+compileJSPsOption]
    else:
        # Web server name specified so create server mapping to include web server
        webModulesToServersMap = [[webModule, webModuleUri, webServer, + server]]
        appOptioins += ["-MapWebModToVH", webModToVHMap, "-MapModulesToServers", webModulesToServersMap, "-appname", appName, "-"+compileJSPsOption]
    #endElse 
else:
    # include install config jacl
    print "Install script:    " + installConfigScript
    print "appName is "+appName
    execfile(installConfigScript )
    # Assumes default server mapping or overridden in install config jacl
    # print "webModulesToServersMap is "+webModulesToServersMap
    # Assumes default virtual host mapping or overridden in install config jacl
    # Shared libraries already appended and displayed in install config jacl if specified
    # EJB Deploy options already appended and displayed in install config jacl if specified
    appOptioins = ["-MapWebModToVH", webModToVHMap, "-node", nodeName, "-server", serverName, "-appname", appName, "-"+compileJSPsOption]
    # append appOptioins [list -MapWebModToVH $webModToVHMap -MapModulesToServers $webModulesToServersMap -appname $appName -$compileJSPsOption]
    # puts "$appOptioins"
#endElse 

print "webModToVHMap:      ",
firstLine = -1
for item in webModToVHMap:
    if (firstLine == 0) : print "                    ",
    print item
    firstLine = 0

node = ""
#if (len(node) == 0):
    # raise Exception("Error -- node " + nodeName + " not found")
    #?PROBLEM? (jacl 80) COMMAND_UNKNOWN?  java::throw(Exception( "Error -- node not found for name "+nodeName ) #?PROBLEM? (jacl 80) JAVA::NEW Exception might require:  from ?? import Exception )
    # return
#endIf 

print "compileJSPsOption:   " + compileJSPsOption
print ""
print earFile
