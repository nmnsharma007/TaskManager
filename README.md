# TasksManager
## Android App for storing tasks

The App is made using the Java language. It uses SQLite database to store the tasks and 
display it to the user using a recycler view. The information displayed includes:
- Task Title
- status of completion

The users have the option to create a new task, update an existing task or delete the task. The
operations on the database are performed on a background thread so that the UI doesn't freeze. Singleton
pattern was used since only one instance of the database is needed to coordinate actions across the App.
The App also uses ViewModel so that database request is not made again when the device configuration 
changes like rotation of the screen. It also uses LiveData to track the changes in the database so that
whenever the database changes, the list of tasks displayed is updated.
