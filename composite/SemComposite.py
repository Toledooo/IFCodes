def create_file(name):
    return {"type": "file", "name": name}

def create_folder(name):
    return {"type": "folder", "name": name, "children": []}

def add(folder, item):
    if folder["type"] == "folder":
        folder["children"].append(item)

def remove(folder, item):
    if folder["type"] == "folder":
        folder["children"].remove(item)

def show_tree(tree, prefix="", is_last=True):
    connector = "└── " if is_last else "├── "
    print(prefix + connector + tree["name"])
    
    if tree["type"] == "folder":
        new_prefix = prefix + ("    " if is_last else "│   ")
        for i, child in enumerate(tree["children"]):
            show_tree(child, new_prefix, i == len(tree["children"]) - 1)

# Exemplo

root = create_folder("Documents")

file1 = create_file("file1.txt")
file2 = create_file("file2.txt")
file3 = create_file("file3.txt")
file4 = create_file("file4.txt")

folder1 = create_folder("folder1")
add(folder1, file1)
add(folder1, file2)

subfolder = create_folder("subfolder")
add(subfolder, file3)

folder2 = create_folder("folder2")
add(folder2, subfolder)

add(root, folder1)
add(root, folder2)
add(root, file4)

remove(subfolder, file3)

show_tree(root)