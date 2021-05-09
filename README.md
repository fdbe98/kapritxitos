# Kapritxitos

_This project corresponds to the subjects of T.I.T. and T.A.P. of the fourth year of Telecommunications Engineering at UPV/EHU._

## Project Files 📄

_This repository contains two main directories:_

* Java Project - contains the java project
* Web Scraping - contains all files related to Python Web Scraping

## Installation 🔧

_The following steps show how to install and configure the Java Project in Eclipse IDE:_

_First download the project from this repository:_
```
git clone https://github.com/fdbe98/kapritxitos.git
```
_Then open Eclipse and import the project._

_Once done, go to the project's prorperties and make sure all following files are included:_

_**Java Build Path, in Classpath:**_

- Jar file: **json-2020115.jar** - [download it from here](https://mvnrepository.com/artifact/org.json/json/20201115)

- **EclipseLink 2.5.2** Library - [download it from here](https://www.eclipse.org/downloads/download.php?file=/rt/eclipselink/releases/2.5.2/eclipselink-2.5.2.v20140319-9ad6abd.zip)

- **WildFly 19 Runtime** Library - [download it from here](https://download.jboss.org/wildfly/19.0.0.Final/wildfly-19.0.0.Final.tar.gz)
   
_**Web Deployment Assembly:**_

- Jar file: **json-2020115.jar**

- **EclipseLink 2.5.2** library

_**Project Facets:**_
- Enable **JAXB**: --> Further Configuration availble --> select **User Library** --> choose **EclipseLink 2.5.2**


Finally, change the filepaths that point to the CSV/JSON files.
* "Distance.java" for CSV files
* "Methods.java" for JSON files

## Authors ✒️
* **Francisco de Borja Esteban** - festeban002@ikasle.ehu.eus
* **Guillermo López-Areal López-Abadía** - glopezareal001@ikasle.ehu.eus
* **Victoria Ansoleaga Ríos** - vansoleaga001@ikasle.ehu.eus
* **Jose Javier González Teomiro** - jgonzalez205@ikasle.ehu.eus


