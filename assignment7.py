from typing import List, Dict, Set
from collections import defaultdict
from functools import reduce
import random
import time


# ---------------- RANDOM LOG GENERATION ----------------
def generate_random_logs(k: int) -> List[Dict[str, object]]:
    actions = ["YouTube", "Instagram", "LeetCode", "WhatsApp", "Netflix", "Gaming"]
    logs = []

    # Generate unique roll numbers like CSB24001, CSB24035, CSB24050, etc.
    roll_numbers = random.sample(range(24001, 24999), k)
    users = [f"CSB{num}" for num in roll_numbers]

    for user in users:
        activities_count = random.randint(2, 4)
        selected_actions = random.sample(actions, activities_count)

        for action in selected_actions:
            logs.append({
                "user": user,
                "action": action,
                "duration": round(random.uniform(0.5, 4.0), 2)
            })

    return logs


# ---------------- TOTAL TIME PER USER ----------------
def total_time_per_user(logs: List[Dict[str, object]]) -> Dict[str, float]:
    def reducer(acc: Dict[str, float], log: Dict[str, object]) -> Dict[str, float]:
        acc[log["user"]] += log["duration"]
        return acc

    return dict(reduce(reducer, logs, defaultdict(float)))


# ---------------- TOP K ACTIVE USERS ----------------
def most_active_users(logs: List[Dict[str, object]], k: int) -> List[str]:
    totals = total_time_per_user(logs)
    return [
        user
        for user, _ in sorted(
            totals.items(),
            key=lambda item: item[1],
            reverse=True
        )[:k]
    ]


# ---------------- UNIQUE ACTIONS ----------------
def unique_actions(logs: List[Dict[str, object]]) -> Set[str]:
    return {log["action"] for log in logs}


# ---------------- DISPLAY LOGS GROUPED BY USER ----------------
def display_logs(logs: List[Dict[str, object]]):
    users = defaultdict(list)

    for log in logs:
        users[log["user"]].append((log["action"], log["duration"]))

    print("\n========== STUDENT ACTIVITY RECORDS ==========")

    for user in sorted(users.keys()):  # Sorted by user
        print(f"\n{user}")
        print("----------------------------------")
        for action, duration in users[user]:
            print(f"{action} ({duration} hrs)")
        print("----------------------------------")


# ---------------- MAIN PROGRAM ----------------
K = int(input("Enter number of random users to generate: "))

# Generate random logs
logs = generate_random_logs(K)

# Display all activity logs grouped by user
display_logs(logs)

# ---------- ANALYSIS ----------
print("\n========== ANALYSIS ==========")

print("\nTotal Time Per User:")
totals = total_time_per_user(logs)
for user, t in sorted(totals.items()):
    print(f"{user}: {t} hrs")

print("\nTop Active Users:")
top_users = most_active_users(logs, K)
for i, user in enumerate(top_users, 1):
    print(f"{i}. {user}")

print("\nUnique Actions:")
for action in sorted(unique_actions(logs)):
    print(action)


# ---------- EXPERIMENTAL COMPLEXITY ANALYSIS ----------
print("\n========== EXPERIMENTAL COMPLEXITY ANALYSIS ==========")

step = max(1, K // 5)  # 5 test sizes

for k in range(step, K + 1, step):
    logs_test = generate_random_logs(k)
    start = time.time()
    most_active_users(logs_test, k)
    end = time.time()
    print(f"Users: {k} | Execution Time: {(end-start):.6f} seconds")


# ---------------- THEORETICAL COMPLEXITY ----------------
print("\n========== THEORETICAL COMPLEXITY ==========")
print("Time Complexity for Top K Users: O(n + m log m)")
print("Space Complexity: O(m)")
