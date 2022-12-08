with open("input.txt") as file_in:
    lines = []
    for line in file_in:
        cols = []
        for letter in line:
            if letter != '\n':
                cols.append(int(letter))
        lines.append(cols)

    largest_scenic_score = 0
    largest_i_j = (0, 0)

    for i in range(99):
        for j in range(99):
            current_tree_height = lines[i][j]
            scenic_left = 0
            scenic_right = 0
            scenic_up = 0
            scenic_down = 0
            for left in range(j - 1, -1, -1):
                scenic_left += 1
                if current_tree_height <= lines[i][left]:
                    break
            for right in range(j + 1, 99):
                scenic_right += 1
                if current_tree_height <= lines[i][right]:
                    break
            for up in range(i - 1, -1, -1):
                scenic_up += 1
                if current_tree_height <= lines[up][j]:
                    break
            for down in range(i + 1, 99):
                scenic_down += 1
                if current_tree_height <= lines[down][j]:
                    break
            scenic_score = scenic_up * scenic_down * scenic_right * scenic_left
            if scenic_score > largest_scenic_score:
                largest_scenic_score = scenic_score
                largest_i_j = (i, j)

    print(largest_scenic_score)
    print(largest_i_j)
