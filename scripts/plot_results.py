
import csv
from pathlib import Path
import matplotlib.pyplot as plt

ROOT = Path(__file__).resolve().parent.parent
CSV_FILE = ROOT / "results" / "results.csv"
PLOTS_DIR = ROOT / "results" / "plots"

PLOTS_DIR.mkdir(parents=True, exist_ok=True)

with CSV_FILE.open("r", encoding="utf-8-sig", newline="") as file:
    reader = csv.DictReader(file)
    rows = [
        row for row in reader
        if row.get("workload", "").strip()
    ]

print(f"Loaded {len(rows)} benchmark rows")

for row in rows:
    row["n"] = (row["n"])
    row["time_ms"] = float(row["time_ms"])


def plot(title, filename, data):
    if not data:
        print(f"No data: {filename}")
        return

    plt.figure(figsize=(9, 5))

    for structure in sorted(set(r["structure"] for r in data)):
        points = sorted(
            [r for r in data if r["structure"] == structure],
            key=lambda r: r["n"]
        )

        plt.plot(
            [r["n"] for r in points],
            [r["time_ms"] for r in points],
            marker="o",
            label=structure
        )

    plt.title(title)
    plt.xlabel("Input size (n)")
    plt.ylabel("Time (ms)")
    plt.xscale("log")
    plt.yscale("log")
    plt.grid(True, which="both", linestyle="--", alpha=0.4)
    plt.legend()
    plt.tight_layout()
    plt.savefig(PLOTS_DIR / filename, dpi=300)
    plt.close()

    print(f"Created: {filename}")


plot(
    "W1 - Random Access",
    "W1_random_access.png",
    [r for r in rows if r["workload"] == "W1"]
)

plot(
    "W2 - Search",
    "W2_search.png",
    [r for r in rows if r["workload"] == "W2"]
)

plot(
    "W3 - Insert/Remove at Head",
    "W3_head.png",
    [
        r for r in rows
        if r["workload"] == "W3" and r["variant"] == "head"
    ]
)

plot(
    "W3 - Insert/Remove at Middle",
    "W3_middle.png",
    [
        r for r in rows
        if r["workload"] == "W3" and r["variant"] == "middle"
    ]
)

plot(
    "W4 - MinHeap",
    "W4_min_heap.png",
    [r for r in rows if r["workload"] == "W4"]
)

print("\nAll plots generated successfully.")