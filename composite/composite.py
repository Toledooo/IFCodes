from component import Component
from typing import List
class Folder(Component):
    def __init__(self, name) -> None:
        super().__init__(name)
        self.__children: List[Component] = []

    @property
    def children(self):
        return self.__children
    
    def is_composite(self) -> bool:
        return True
    
    def remove(self, component: Component) -> None:
        self.children.remove(component)

    def add(self, component: Component) -> None:
        self.children.append(component)
        component.parent = self

    def show_tree(self, prefix="", is_last=True):
        tree = "└── " if is_last else "├── "
        print(prefix + tree + self.name)
        new_prefix = prefix + ("    " if is_last else "│   ")

        for index, child in enumerate(self.children):
            is_last_child = index == len(self.children) - 1
            child.show_tree(new_prefix, is_last_child)