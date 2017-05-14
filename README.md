# T-Partner Session Encapsulator

This fragment of module is responsible for proccess log data of a specific version of MeuTutor, a problem-solving based smart educational system.

The SE (Session Encapsulator) has an executable class called CSVReader that can read a CSV log file with actions performed by students (users of MeuTutor) on the educational platform, line per line, categorizing that student actions into sub-sessions. Sub-sessions are fragments of sessions that make more sense in being real sessions. Sessions, on the other hand, are browser sessions. The location of the CSV log file is set on the main method of CSVReader class.

The need to differenciate sub-sessions from sessions comes when the student can just do not logout from MeuTutor, and that session can stay active for days, even the student being away for a long time. Sub-sessions have a 30 minute time guarantee between an student action and another - "30 minutes" is actually the amount of time between interactions the authors of [this paper](http://www.cs.cmu.edu/~rosie/papers/jonesKlinknerCIKM2008.pdf) found is more trustable to be an unique session.

The database configuration is set on the `spring.xml` file. After running CSVReader class once, you can summarize the student actions per subsessions with this query on PostgreSQL server:
```
SELECT student_action.*,
       sub_session.access_session_id,
       access_session.student_id
FROM student_action
INNER JOIN sub_session ON sub_session.id = sub_session_id
INNER JOIN access_session ON access_session.id = sub_session.access_session_id
WHERE access_session_id > 0
ORDER BY student_id,
         access_session_id,
         sub_session_id,
         date_time;
```
The SE is written in Java, using NetBeans 8.2 on Ubuntu 16.04 LTS. Maven 4.0.0, Hibernate 4.3.6, Spring 3.1.2, GlassFish 4.1.1, Oracle JDK 1.8.0, PostgreSQL Server 9.6 are too being used in development and production of the SE.
