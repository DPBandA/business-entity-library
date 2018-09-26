# General
- Create SystemProperties class that can be sub-classed by SystemOptions class
  in LabelPrint and other apps
- Move dependencies and code that do not belong to other apps such as jmts.
  jaxen and POI could be moved.
- Move the authentication code from JobManager in JMTS to Authentication class
  and use it.
- Add Notification class. 