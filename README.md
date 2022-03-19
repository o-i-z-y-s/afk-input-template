# afk-input-template
This is a configurable script for clicking relentlessly or typing intermittently while away from the computer. It is intended be run from an IDE such as VS Code, as it needs to be modified before execution. The desired function must be uncommented in the code. Follow the comments to customize it for your uses!

## Why?
The clicking component:
- Carpal tunnel bad. Tedium bad. When games require you to click incessantly, your joints, mouse, and wits will pay the price. This script allows you to set the number of clicks, the time between clicks, and how often progress is reported. 

The typing component:
- Sometimes you just need to stay logged in or active. I actually added this functionality during Final Fantasy XIV's Great Login Server Debacle of 2021, and it served its purpose just fine.

## Configuration
In order to start the script, you'll need to uncomment one of the two functions in ```main()```: ```autoclick()``` or ```autoKeys()```. From there, each function has comments beside its configurable elements explaining what they're for.

**Warning:** There is currently no way to abort the clickening once it has begun due to limitations inherent in Java and the Robot class. *Be sure your ```clickCount``` is correct before execution.*
