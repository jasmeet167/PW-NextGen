Option Explicit

' Declarations
' WSH (Windows Scripting Host) related
Dim oWshShell
Dim exitcode : exitcode = 0

Dim oService, oArgs
Dim oServiceList, errReturn
Dim oSWbemLocator, oSWbemServices

Set oArgs = WScript.Arguments

' WSH setup
Set oWshShell = CreateObject("WScript.Shell")

Set oSWbemLocator = CreateObject("WbemScripting.SWbemLocator")
Set oSWbemServices = oSWbemLocator.ConnectServer(oArgs(0), "root\cimv2", oArgs(2), oArgs(3), "MS_409", "ntlmdomain:" + oArgs(1))


Class Global
  Private Sub Class_Initialize()
    If (oArgs.count < 1) Then
      WScript.Echo "Machine name required"
      WScript.Quit(1)
    End If
    If (oArgs.count < 2) Then
      WScript.Echo "User domain required"
      WScript.Quit(1)
    End If
    If (oArgs.count < 3) Then
      WScript.Echo "User name required"
      WScript.Quit(1)
    End If
    If (oArgs.count < 4) Then
      WScript.Echo "User password required"
      WScript.Quit(1)
    End If
    If (oArgs.count < 5) Then
      WScript.Echo "Service name required"
      WScript.Quit(1)
    End If
    If (oArgs.count < 6) Then
      WScript.Echo "Action required"
      WScript.Quit(1)
    End If
    ' Get service
    Set oServiceList = oSWbemServices.ExecQuery("Select * from Win32_Service where Name='" &oArgs(4)  & "'")
   ' Start or stop service
    For Each oService In oServiceList
      If (UCase(oArgs(5)) = "START") Then     
        WScript.Echo "Starting " &oArgs(4)  & " service..."
        errReturn = oService.StartService()
      Else
        WScript.Echo "Stopping " &oArgs(4)  & " service..."
        errReturn = oService.StopService()
      End If
    Next    
  End Sub
  
  Private Sub Class_Terminate()
    exitcode = Err.Number
  End Sub
End Class

Dim Main : Set Main = New Global
Set Main = Nothing
WScript.Quit exitcode
