# General
- Move the authentication code from JobManager in JMTS to Authentication class
  and use it. Continue implementation.
- Rename JobManagerUser to User and change table name if possible.
- Create SystemProperties class that can be sub-classed by SystemOptions class
  in LabelPrint and other apps
- Move dependencies and code that do not belong to other apps such as jmts.
  jaxen and POI could be moved.
- Add Notification class. 
- Use "Infrastructure Software Modules for Enterprises (ch1)" to document.
- Rename BusinessEntity to Entity through refactoring??