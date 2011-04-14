# NetBeans CouchApp Module

Create [CouchApp](http://github.com/couchapp/couchapp)'s for CouchDB inside NetBeans.

Checkout the [Wiki](https://github.com/pangratz/netbeans-couchapp/wiki) for infos about usage and the roadmap.

## Installation

* clone the project
* init the submodule via **git submodule init**
* checkout the latest submodule via **git submodule update**
* execute **mvn install** to create the netbeans module, located in the **target** folder
* NOTE: to successfully create the netbeans module, the tests are executed which implies that a CouchDB instance is running locally on port standard 5984

## ... or

you download the already created NBM bundle from the downloads section [netbeans-couchapp-module-0.5-SNAPSHOT.nbm](https://github.com/downloads/pangratz/netbeans-couchapp/netbeans-couchapp-module-0.5-SNAPSHOT.nbm)
