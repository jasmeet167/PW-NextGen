Option Explicit

Const MAX_ITER = 30
Dim oArgs
Dim oWMIService
Dim oService, oServiceList
Dim iCount,bDone

Set oArgs = WScript.Arguments

Set oWMIService = GetObject("winmgmts:{impersonationLevel=impersonate}!\\.\root\cimv2")
Set oServiceList = oWMIService.ExecQuery("Select * from Win32_Service Where Name = '" + oArgs(0) + "'")

For Each oService In oServiceList
    If oService.State <> "Stopped" Then
        WScript.StdErr.Write "Stopping " +  oArgs(0) + " service..."
        oService.StopService
	Else
        WScript.StdErr.Write oArgs(0) + " service not running"
    End If
Next

iCount = 0
Do
    Set oServiceList = oWMIService.ExecQuery("Select * from Win32_Service Where Name = '" + oArgs(0) + "'")

    bDone = True
    For Each oService In oServiceList
        If oService.State <> "Stopped" Then
            WScript.StdErr.Write "..."
            bDone = False
            WScript.Sleep 500
            Exit For
        End If
    Next

    iCount = iCount + 1
    If iCount > MAX_ITER Then
        WScript.StdErr.Write ""
        WScript.StdErr.Write "Timed out waiting for " + oArgs(0)
        Exit Do
    End If
Loop Until bDone
WScript.StdErr.Write ""
