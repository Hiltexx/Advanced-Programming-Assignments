import sys
import gc


class Node:
    def __init__(self, name):
        self.name = name
        self.link = None

    def __del__(self):
        print(f"{self.name} is being garbage collected")


# Disable automatic garbage collection
gc.disable()

print("--- Objects Created ---\n")

A = Node("Node A")
B = Node("Node B")

# Create cyclic reference
A.link = B
B.link = A

print("A ->", A.link.name)
print("B ->", B.link.name)

print("\n--- Reference Count ---\n")

# sys.getrefcount() adds one temporary reference
print("A :", sys.getrefcount(A))
print("B :", sys.getrefcount(B))

print("\n--- Deleting References ---\n")

# Store IDs for investigation
a_id = id(A)
b_id = id(B)

# Delete main references
del A
del B

print("A and B deleted")

print("\n--- Before Garbage Collection ---\n")

found = False

for obj in gc.get_objects():
    if id(obj) == a_id or id(obj) == b_id:
        print("Still in memory :", obj.name)
        found = True

if not found:
    print("Objects not found")

print("\n--- Running Garbage Collector ---\n")

unreachable = gc.collect()

print("\nObjects collected :", unreachable)

print("\n--- After Garbage Collection ---\n")

found = False

for obj in gc.get_objects():
    if id(obj) == a_id or id(obj) == b_id:
        print("Still exists :", obj.name)
        found = True

if not found:
    print("Cycle removed from memory")

# Re-enable automatic garbage collection
gc.enable()
