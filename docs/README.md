# Koya User Guide

// Product screenshot goes here

Koya is a personnal assistant chatbot that keeps track of the user's task via Command Line Interface (CLI)

## Features
Notes about the command format:

All parameters that must be input by the user are CAPITALIZED. Carefully follow the format for each feature.

e.g. deadline /by DESCRIPTION , DESCRIPTION is the parameter. The user may for instance input: deadline /by Monday 3PM

### Adding a deadline: deadline

Adds a deadline task to the list

Format: deadline /by DESCRIPTION

Example: deadline homework /by Monday 3PM

// A description of the expected outcome goes here

```
expected output
```

### Adding a todo: todo

Adds a todo task to the list

Format: todo DESCRIPTION

Example: todo dishes 

// A description of the expected outcome goes here

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

// A description of the expected outcome goes here

```
event gala /from Monday 3PM /to Monday 6PM
Got it. I've added this task:
 [E][ ] gala (from: Monday 3PM to: Monday 6PM)
Now you have 2 tasks in the list.
```

### Deleting a task: delete

Deletes a task from the list

Format: remove TASK_INDEX

Example: delete 1

// A description of the expected outcome goes here

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
Nice! I've marked this as done:
[E][X] gala (from: Monday 3PM to: Monday 6PM)
```

### Finding tasks given its description: find

List all task which has the given description

format: find DESCRIPTION

Example: find homework

// A description of the expected outcome goes here

```
expected output
```

### Listing all the tasks from the list: list

List all the tasks from the list

Format: list

Example: list

// A description of the expected outcome goes here

```
expected output
```
