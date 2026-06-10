products = []
while True:
print("\n===== Product Management Menu =====")
print("1. Add Product")
print("2. View All Products")
print("3. Show Products with Stock Less Than 10")
print("4. Exit")
choice = input("Enter your choice (1-4): ")
if choice == "1":
name = input("Enter product name: ")
quantity = int(input("Enter stock quantity: "))
product = {"name": name, "stock": quantity}
products.append(product)
print("Product added successfully!")
elif choice == "2":
if not products:

print("No products stored yet.")
else:
print("\nStored Products:")
for product in products:
print(f"Product: {product['name']}, Stock:
{product['stock']}")
elif choice == "3":
found = False
print("\nProducts with stock less than 10:")
for product in products:
if product["stock"] < 10:
print(f"Product: {product['name']}, Stock:
{product['stock']}")
found = True
if not found:
print("No products with stock less than 10.")
elif choice == "4":
print("Exiting program...")
break
else:
print("Invalid choice! Please enter 1-4.")