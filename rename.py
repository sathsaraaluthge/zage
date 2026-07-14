import os
import glob

replacements = {
    "UserRequestDto": "StaffRequestDto",
    "UserResponseDto": "StaffResponseDto",
    "UserController": "StaffController",
    "UserService": "StaffService",
    "UserServiceImpl": "StaffServiceImpl",
    "UserRepository": "StaffRepository",
    "userRepository": "staffRepository",
    "userService": "staffService",
    "createUser": "createStaff",
    "getUser": "getStaff",
    "getUsers": "getAllStaff",
    "deleteUser": "deleteStaff",
    "User created": "Staff created",
    "User fetched": "Staff fetched",
    "Users fetched": "Staff fetched",
    "User deleted": "Staff deleted",
    "User not found": "Staff not found",
    "saveUser": "saveStaff",
    "userId": "staffId",
    "user_login": "staff_login",
    "user_id": "staff_id",
    "users": "staff",
    "user": "staff",
    "User": "Staff"
}

files = glob.glob("src/main/java/**/*.java", recursive=True)

for file in files:
    with open(file, 'r') as f:
        content = f.read()
    
    new_content = content
    for old, new in replacements.items():
        new_content = new_content.replace(old, new)
        
    if new_content != content:
        with open(file, 'w') as f:
            f.write(new_content)
        print(f"Updated content of {file}")
    
    # Rename file if needed
    basename = os.path.basename(file)
    if "User" in basename:
        new_basename = basename.replace("User", "Staff")
        new_path = os.path.join(os.path.dirname(file), new_basename)
        os.rename(file, new_path)
        print(f"Renamed {file} to {new_path}")

