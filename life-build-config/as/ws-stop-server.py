import sys

global AdminControl

nodeName = sys.argv[0]
serverName = sys.argv[1]

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