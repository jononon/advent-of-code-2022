with open("input.txt") as file_in:
    cycle_check = [20, 60, 100, 140, 180, 220]
    global_cycles = 0
    x = 1
    signal_strength = 0

    for line in file_in:
        command = line.split(" ")
        cycles = 1 if command[0][0:4] == "noop" else 2
        for i in range(cycles):
            global_cycles += 1
            if global_cycles in cycle_check:
                signal_strength += x * global_cycles
        if command[0][0:4] == "addx":
            x += int(command[1])

    print(signal_strength)
