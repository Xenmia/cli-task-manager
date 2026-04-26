A simple cli task manager project to get familiar with Java, OOP, GSON and I/O

Usage:
    - list/ls/view: shows the current list of tasks (usage: ls)
    - add: adds a task to the task list (usage: add <task-content> [flags])
        - -p/--priority: immediately marks the task as high priority at creation (usage: add <task-content> -p)
        - -c/--completed: immediately marks the task as completed at creation (usage: add <task-content> -c)
    - remove/rm: removes a task(usage: rm <index>)
    - complete/comp: marks a task as completed (usage: comp <index>)
    - priority/pr: toggles the task's priority status (usage: pr <index>)
    - exit/quit/q: leaves the programs (usage: q)