# EZStarter
Java class to start aiwolf server and clients at the same time

### Usage

   ```
   -p port -n playerNum -c clientClass [requestRole] ( -c clientClass [requestRole] ...)
   ```
   
   port : Port number of server
   
   playerNum : Total number of players
   
   clientClass : Name of player class
   
   requestRole (optional) : Specified role of player

### Example

  If the number of clients is less than playerNum,
  the server waits connection until vacant clients are filled.
  
  ```
  java -jar EZStarter.jar -p 12345 -n 5 -c org.aiwolf.client.base.smpl.SampleRoleAssignPlayer -c org.aiwolf.client.base.smpl.SampleRoleAssignPlayer -c org.aiwolf.client.base.smpl.SampleRoleAssignPlayer -c org.aiwolf.client.base.smpl.SampleRoleAssignPlayer
  ```
  
  In this example, server has accepted four connections from SampleRoleAssignPlayer,
  and is waiting one connection at port 12345.
