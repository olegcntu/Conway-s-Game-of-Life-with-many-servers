# Conway-s-Game-of-Life-with-many-servers
![image](https://user-images.githubusercontent.com/66881230/165101304-2bf7b4c6-ca85-444f-b253-9b32749fe7cd.png)
Conway's Game of Life is a cellular automaton invented by English mathematician John Conway in 1970.
The place of action of the game is a plane marked into cells, which can be unlimited, limited, or closed.
Each cell on this surface has eight neighbors surrounding it, and can be in two states: to be "alive" (filled) or "dead" (empty).
The distribution of living cells at the beginning of the game is called the first generation. Each next generation is calculated based on the previous one according to the following rules:
in an empty (dead) cell, which is adjacent to three living cells, life is born;
if a living cell has two or three living neighbors, then this cell continues to live; otherwise (if there are less than two or more than three living neighbors), the cell dies (“from loneliness” or “from overcrowding”).
The game ends if
not a single “living” cell will remain on the field;
the configuration at the next step will exactly (without shifts and rotations) repeat itself at one of the earlier steps (a periodic configuration is added)
at the next step, none of the cells changes its state (the previous rule applies one step back, a stable configuration is formed)

To start the game, you must first start the server part and specify the number of servers to run. After that, you need to launch the application itself and indicate how many servers you need to split the task execution, which will be done automatically.
