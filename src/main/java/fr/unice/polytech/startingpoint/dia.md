```mermaid

classDiagram
IDistrict <|-- District
IWonder   <|-- Wonder
IDistrict <|-- IWonder

    class IDistrict{
      +int getVal()
      +Enum getColor()
      +boolean isWonder()
    }
    
    class District{
      -int val
      -Enum color
      -String name
      +District(int val, Enum color, String name)
    }

    class Wonder{
        -int val
        -Enum color
        -String name
        -String description
        +Wonder(int val, Enum color, String name, String Description)
    }

    class IWonder{
      +doAction()
      +effectOfAction()
    }
    
```     