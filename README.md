# In-Memory Expense Tracker CLI

A simple command-line expense tracker built with Java. Supports adding, viewing, filtering, and exporting expenses — all stored in-memory but persisted to a JSON file using Jackson.

---

## Features

- Add new expenses with title, amount, category, and optional description
- View all expenses
- Filter by category
- Filter by date range
- Remove expenses by index
- View category-wise breakdown
- Get total spent
- Export expenses to CSV
- Persistent storage to `expenses.json` file

---

## Requirements

- Java 21+
- Maven

---

## Setup

1. Clone the repository
```bash
git clone https://github.com/fa635/Project-In-Memory-Expense-Tracker-CLI-2.0-.git
cd Project-In-Memory-Expense-Tracker-CLI-2.0-
```

2. Install dependencies and compile:
```bash
mvn clean install
```

3. Add this function to your terminal config (`.bashrc`, `.zshrc`, etc.) to simplify running the app:
```bash
function app {
    java -cp "target/classes;target/dependency/*" com.fa.App $args
}
```

Reload your shell:
```bash
source ~/.bashrc  # or ~/.zshrc
```
OR
paste the function into poweshell (requires this process at each session)

---

## Usage

### Add an Expense
```bash
app add "Lunch" 12.50 food "Had tacos"
```

> If description is omitted, it's saved as an empty string.

---

### View All Expenses
```bash
app all
```

---

### Filter by Category
```bash
app byCategory food
```

---

### Filter by Date Range
```bash
app byDateRange 2025-04-01 2025-04-21
```

---

### Remove Expense by Index
```bash
app remove 2
```

---

### Total Spent
```bash
app total
```

---

### Category Breakdown
```bash
app categoryBreakdown
```

---

### Export to CSV
```bash
app exportCSV
```
Creates `expenses.csv` in the project root.

---

## Expense Categories

- `FOOD`
- `TRANSPORT`
- `UTILITIES`
- `ENTERTAINMENT`
- `OTHER`

> Defaults to `OTHER` if invalid category is passed.

---

## Data Persistence

- Expenses are stored in `expenses.json` using Jackson
- LocalDate support enabled via `jackson-datatype-jsr310`

---

## License
MIT — This project is open-source and free to use.
