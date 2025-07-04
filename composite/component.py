from __future__ import annotations
from abc import ABC, abstractmethod

class Component(ABC):
    def __init__(self,name):
        self.name = name

    @property
    def parent(self) -> Component:
        return self.__parent
    
    @parent.setter
    def parent(self, parent: Component):
        self.__parent = parent

    def is_composite(self) -> bool:
        return False
    
    @abstractmethod
    def add(self, component: Component) -> None:
        pass
    
    @abstractmethod
    def remove(self, component: Component) -> None:
        pass

    @abstractmethod
    def show_tree(self, prefix="", is_last=True):
        pass