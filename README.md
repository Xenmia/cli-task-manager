# CLI Task Manager

A simple command-line task manager built to explore **Java**, **OOP**, **GSON**, and **I/O**.

---

## Usage

### Viewing Tasks

| Command | Description | Example |
|---------|-------------|---------|
| `list` / `ls` / `view` | Show all current tasks | `ls` |

### Managing Tasks

| Command | Description | Example |
|---------|-------------|---------|
| `add` | Add a new task | `add Buy groceries` |
| `remove` / `rm` | Remove a task by index | `rm 2` |
| `complete` / `comp` | Mark a task as completed | `comp 1` |
| `priority` / `pr` | Toggle a task's priority status | `pr 3` |
| `exit` / `quit` / `q` | Exit the program | `q` |

### `add` Flags

| Flag | Description | Example |
|------|-------------|---------|
| `-p` / `--priority` | Mark as high priority on creation | `add Buy groceries -p` |
| `-c` / `--completed` | Mark as completed on creation | `add Buy groceries -c` |