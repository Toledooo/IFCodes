from composite import Folder
from leaf import File

root = Folder("Documents")

file1 = File("file1.txt")
file2 = File("file2.txt")
file3 = File("file3.txt")
file4 = File("file4.txt")

folder1 = Folder("folder1")
folder1.add(file1)
folder1.add(file2)

folder2 = Folder("folder2")
subfolder = Folder("subfolder")
subfolder.add(file3)
folder2.add(subfolder)

root.add(folder1)
root.add(folder2)
root.add(file4)

print("Árvore antes da remoção do file3:")
root.show_tree()

print("\nÁrvore depois da remoção:")
subfolder.remove(file3)
root.show_tree()

print("\nValidação da pasta pai:")
print(file1.parent.name)
print(file4.parent.name)