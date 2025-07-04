from component import Component
class File(Component):
    def add(self, component: Component) -> None:
        raise Exception("File can't contain children")
    
    def remove(self, component:Component) -> None:
        raise Exception("File has no children")

    def show_tree(self, prefix="", is_last=True):
        tree = f"└──" if is_last else "├──"
        print(prefix + tree + self.name)