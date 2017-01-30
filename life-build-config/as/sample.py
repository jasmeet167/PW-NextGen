#############################################
#
#  Python configuration script for Websphere
#
#  Prerequisites:
#
#  1) All nodes must be added with addNode.sh
#     prior to running this script
#
#  2) All desired configuration parameters
#     must be set in the configuration
#     section
#
#  Postrequisites:
#
#  1) Run setprops.py to set properties
#
#############################################

#############################################
# CONFIGURATION VARIABLES
#
# THESE VARIABLES MUST BE SET BEFORE 
# EXECUTING THIS SCRIPT
#############################################

CELL = "xxxxxxxxxxx"
CLUSTER = "xxxxxxxxxxxx"
SERVER_PREFIX = "xxxxxxxxxx"
NODES = "Node1,Node1"
DEPLOYMENT_MANAGER = "dmgr"
START_PORT_HTTP = 19200       
START_PORT_HTTPS = 19440
SERVERS_PER_NODE = 1

#############################################
# DO NOT MODIFY BELOW THIS LINE
#############################################

import  java.lang.System  as  sys
lineSeparator = sys.getProperty('line.separator')

#############################################
# MAIN PROGRAM STARTS HERE
#############################################
 
def setPorts(server,index):
    print ""
    print "Setting HTTP ports..."
    wc = AdminConfig.list('WebContainer', server)
    transportsAttr = AdminConfig.showAttribute(wc, 'transports')
    transports = transportsAttr[1:len(transportsAttr)-1]
    transport = transports.split(" ")[0]
    print "Setting HTTP port = " + str(START_PORT_HTTP + (index * 10))
    AdminConfig.modify(transport, [['address', [['host', '*'], ['port', START_PORT_HTTP + (index * 10)]]]])
    transport = transports.split(" ")[1]
    print "Setting HTTPS port = " + str(START_PORT_HTTPS + (index * 10))
    AdminConfig.modify(transport, [['address', [['host', '*'], ['port', START_PORT_HTTPS + (index * 10)]]]])
    print "Done setting ports..."
    

#############################################
# Create a cluster within the cell
#############################################

# Check if cluster already exists
#      If not, create
#      If so, proceed

clusterList = AdminControl.completeObjectName("cell=" + CELL + ",type=Cluster,name=" + CLUSTER + ",*")

if len(clusterList) == 0:
  cellID = AdminConfig.getid("/Cell:" + CELL + "/")
  clusterName = "[[name " + CLUSTER + "]]"
  print ""
  print "Creating cluster " + CLUSTER + " in cell " + CELL
  AdminConfig.create('ServerCluster', cellID, clusterName)
else:
  print ""
  print "Cluster " + CLUSTER + " already exists, proceeding."

############################################
# Add Application Servers to the cluster
############################################

print ""
print "Creating application server(s) in cluster " + CLUSTER
clusterID = AdminConfig.getid("/ServerCluster:" + CLUSTER + "/")

nodeList = NODES.split(",")
index = 1
for nodeName in nodeList:
   node = AdminConfig.getid("/Node:" + nodeName + "/")
   loop = 0
   while (loop != SERVERS_PER_NODE):
      uid = "%s" % (index)
      servName = SERVER_PREFIX + uid
      serverID = AdminConfig.getid('/Server:' + servName + '/')
      if len(serverID) == 0:
         nameAttr = ["memberName", servName]
         attrs = []
         attrs.append(nameAttr)
         print "Creating server " + servName + " on node " + nodeName
         server = AdminConfig.createClusterMember(clusterID, node, attrs)
      else:
         print "Server " + servName + " already exists, proceeding."

      loop = loop + 1
      index = index + 1

   ###########################################
   # Save and sync changes
   ###########################################
   print "Saving configuration..."
   AdminConfig.save()
   print " "
   print " "

####################################################
# Ask the ClusterMgr to refresh its list of clusters 
#################################################### 

clusterMgr = AdminControl.completeObjectName("type=ClusterMgr,cell=" + CELL + ",*")
if len(clusterMgr) == 0:
   print "Error -- clusterMgr MBean not found for cell " + CELL
else:
   AdminControl.invoke(clusterMgr, "retrieveClusters")

##########################################################
# For each node, invoke a sync if necessary 
#     -- Is a nodeSync MBean available on this node? 
#     -- Find out if serverStartupSyncEnabled is true for this node
#        We just created this server, so if this attribute is set to
#        "false" we have to perform a sync.  If we do not, the node we
#        are installing on may have an out-of-date copy of the config
#        data. 
##########################################################
for nodeName in nodeList:
   node = AdminConfig.getid("/Node:" + nodeName + "/")
   print ""
   print "Checking for the existence of a NodeSync MBean on node " + nodeName
   nodeSync = AdminControl.completeObjectName("type=NodeSync,node=" + nodeName + ",*")
   if len(nodeSync) == 0:
      print "Error -- NodeSync MBean not found for name " + nodeName
   else:
      enabled = AdminControl.getAttribute(nodeSync, "serverStartupSyncEnabled")
      if enabled == "false":
         print ""
         print "Invoking synchronization for node " + nodeSync + " because serverStartupSyncEnabled is set to false..."
         AdminControl.invoke(nodeSync, "sync")
         sleep(20)
         print "Done with synchronization."
  
##########################################################
# Ask the Cluster MBean to start the cluster
##########################################################
cluster = AdminControl.completeObjectName("type=Cluster,name=" + CLUSTER + ",*")
print ""
print "Invoking start for cluster " + CLUSTER
AdminControl.invoke(cluster, "start")
sleep(30)

##########################################################
# Set the ports
##########################################################

nodeList = NODES.split(",")
index = 1
for nodeName in nodeList:
   node = AdminConfig.getid("/Node:" + nodeName + "/")
   loop = 0
   while (loop != SERVERS_PER_NODE):
      uid = "%s" % (index)
      sid = loop + 1
      servName = SERVER_PREFIX + uid
      #servName = SERVER_PREFIX + 'n' + repr(index) + 's' + repr(sid) + SERVER_SUFFIX
      serverID = AdminConfig.getid('/Server:' + servName + '/')
      setPorts(serverID,index)

      loop = loop + 1
      index = index + 1

   ###########################################
   # Save and sync changes
   ###########################################
   print "Saving configuration..."
   AdminConfig.save()
   print " "
   print " "

##########################################################
# Regenerate the Websphere plugin file
##########################################################
print ""
print "Regenerating the plugin-cfg.xml files..."
nodeList = NODES.split(",")
for nodeName in nodeList:
   node = AdminConfig.getid("/Node:" + nodeName + "/")
   pluginGen = AdminControl.completeObjectName('type=PluginCfgGenerator,*')
   AdminControl.invoke(pluginGen, 'generate', "/opt/WebSphere61/AppServer/profiles/Dmgr01/config" + CELL + " null null plugin-cfg.xml")

##########################################################
# Ask the Cluster MBean to restart the cluster
##########################################################
cluster = AdminControl.completeObjectName("type=Cluster,name=" + CLUSTER + ",*")
print ""
print "Restarting cluster " + CLUSTER
AdminControl.invoke(cluster, "stop")
sleep(30)
AdminControl.invoke(cluster, "start")
sleep(30)

print "***********************************************************************"
print "Cluster successfully created. Please run setprops.py to set properties."
print "***********************************************************************"
