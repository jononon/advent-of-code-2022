with open("input.txt") as file_in:
    lines = []
    for line in file_in:
        cols = []
        for letter in line:
            if letter != '\n':
                cols.append(int(letter))
        lines.append(cols)

    visible_trees = set()

    for i in range(99):
        largest_tree = -1
        for x in range(99):
            if lines[i][x] > largest_tree:
                visible_trees.add((i, x))
                largest_tree = lines[i][x]

    for i in range(99):
        largest_tree = -1
        for x in range(99):
            if lines[i][99-x-1] > largest_tree:
                visible_trees.add((i, 99-x-1))
                largest_tree = lines[i][99-x-1]

    for i in range(99):
        largest_tree = -1
        for x in range(99):
            if lines[x][i] > largest_tree:
                visible_trees.add((x, i))
                largest_tree = lines[x][i]

    for i in range(99):
        largest_tree = -1
        for x in range(99):
            if lines[99-x-1][i] > largest_tree:
                visible_trees.add((99-x-1, i))
                largest_tree = lines[99-x-1][i]

    print(len(visible_trees))
