with open("input.txt") as file_in:
    global_cycles = 0
    x = 1

    for line in file_in:
        command = line.split(" ")
        cycles = 1 if command[0][0:4] == "noop" else 2
        for i in range(cycles):
            if global_cycles % 40 == 0:
                print()
            sprite = [x - 1, x, x + 1]
            if global_cycles % 40 in sprite:
                print("#", end="")
            else:
                print(".", end="")
            global_cycles += 1
        if command[0][0:4] == "addx":
            x += int(command[1])
