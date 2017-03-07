# jsystem-perfecto-plugin

This repository consists of the following Java Maven projects:
* **perfecto-mobile-browser** - A standalone Java application, implementing a web browser which allows interacting with Perfecto Mobile's "Mobile Cloud" website views - "Perfecto Lab" and "Perfecto Dashboard".
* **jsystem-perfecto-plugin** - Adding this plugin to JSystem allows adding Perfecto Mobile's tab inside the JSysten runner GUI. This plugin depends on the **perfecto-mobile-browser** application which launches a web browser that logs-in to Perfecto's Mobile Cloud, gets the generated **executionId** and allows other applications to query for the executionId through a TCP socket.

##perfecto-mobile-browser
![Perfecto Mobile "Lab" and "Dashboard" browsers](https://github.com/ronyb/jsystem-perfecto-plugin/blob/master/images/perfecto_browsers.png)

###Description and use

This is a standalone Java GUI application, based on the [SWT library](https://www.eclipse.org/swt/). The application can be used on its own, or it could be launched from Perfecto's tab inside JSystem runner.

To launch the application, double click on the `perfecto-mobile-browser-<version>.jar` file, or launch it from the command line by typing: `java -jar perfecto-mobile-browser-<version>.jar`.

When launching the application without providing command line arguments, two browser windows are opened: "Perfecto Lab" and "Perfecto Dashboard" - each opens a different URL on the Perfecto Mobile Cloud.

It is possible to launch just one browser - "Perfecto Lab" or "Perfecto Dashboard" by adding a single command line argument:

For "Perfecto Lab": `java -jar perfecto-mobile-browser-<version>.jar lab`<br/>
For "Perfecto Dashboard": `java -jar perfecto-mobile-browser-<version>.jar dashboard`

####perfecto.properties
The application depends on the `perfecto.properties` file which should be located inside the same directory as the JAR file.
The `perfecto.properties` file is used to configure the URL of the Perfecto Cloud and the username and password which would be used by the application to perform automatic log-in inside the web page, by invoking the `doExternalLogin` JavaScript function on the client-side.

####Reading the executionId
The core feature of the application is to communicate with the client-side JavaScript code of the Perfecto application. The application receives and stores the **executionId** that's created after logging-in to "Perfecto Lab". The executionId is stored in memory, and can later be queried by other applications, e.g., code running from a JSystem test.

When launched, the application runs a TCP server, listening on port 3456. The server supprts the following requests from clients:<br>
`getExecutionId` - returns the current executionId<br>
`getCould` - returns the the current Perfeco cloud URL, as was configured in the `perfecto.properties` file<br>
`getCloudAndExecutionId` - returns both the cloud URL and the current executionId

###How to build

###How to deploy


##jsystem-perfecto-plugin
![JSystem Perfecto Mobile Tab](https://github.com/ronyb/jsystem-perfecto-plugin/blob/master/images/jsystem_perfecto_tab.png)

###Description and use

###How to build

###How to deploy
