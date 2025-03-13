# Koya User Guide

Koya is a personnal assistant chatbot that keeps track of the user's task via Command Line Interface (CLI)

## Quick Start
In order to use the chatbot, carefully follow the steps below:
1. Have Java 17 installed on your laptop
, you can verify the java version by running the following command in your terminal:
``` java -version ```
2. Download the .jar file
4. Open a terminal and cd to where the .jar file is located
5. Run the following command java ```-jar "JAR_FILE_NAME"```
6. Type the desired command in the command box (select any commands from the Features section below) and press enter

## Features
Notes about the command format:

1. The program is case sensitive. Carefully follow the format for each features!

2. All parameters that must be input by the user are CAPITALIZED. 

    e.g. deadline /by DESCRIPTION , DESCRIPTION is the parameter. The user may for instance input: deadline /by Monday 3PM


### Adding a deadline: deadline

Adds a deadline task to the list

Format: deadline /by DESCRIPTION

Example: deadline homework /by Monday 3PM

```
Got it. I've added this task:
 [D][ ] homework (by: Monday 3PM)
Now you have 4 tasks in the list.
```

### Adding a todo: todo

Adds a todo task to the list

Format: todo DESCRIPTION

Example: todo dishes 

```
todo dishes
Got it. I've added this task:
 [T][ ] dishes
Now you have 8 tasks in the list.
```

### Adding an event: event
Adds an event to the list

Format: event DESCRIPTION /from START /to END

Example: event gala /from Monday 3PM /to Monday 6PM

```
event gala /from Monday 3PM /to Monday 6PM
Got it. I've added this task:
 [E][ ] gala (from: Monday 3PM to: Monday 6PM)
Now you have 2 tasks in the list.
```
### Listing all the tasks from the list: list

List all the tasks from the list and their respective index

Format: list

Example: list

```
list
Here are the tasks in your list:
1.  [E][X] gala (from: Monday 3PM to: Monday 6PM)
2.  [T][ ] homework CS2113
3.  [D][X] homework (by: Friday 14th)
4.  [T][ ] read book
5.  [D][ ] return book (by: June 6th)
```

### Deleting a task: delete

Deletes a task from the list

Format: remove TASK_INDEX

Example: delete 1

Note: The task's index are automatically adjusted as a task gets deleted. To ensure one is referring to the right task index, perform the list command to view the task's index.

```
delete 1
Noted! I've removed this task:
 [E][ ] gala (from: a to: b)
You only have 1 tasks in your list
```

### Marking a task as done: mark

Marks a task as done

Format: mark TASK_INDEX

Example: mark 1

```
mark 1
Nice! I've marked this as done:
[E][X] gala (from: Monday 3PM to: Monday 6PM)
```

### Finding tasks given its description: find

List all task which has the given description

Format: find DESCRIPTION

Example: find homework


```
find book
Here are the matching tasks in your list:
1.  [T][ ] read book
2.  [D][ ] return book (by: June 6th)
```


### Exit the chatbot:  bye

Exit the chatbot

Format: bye

Example: bye

```
bye
Bye Bye! See you soon!
```
