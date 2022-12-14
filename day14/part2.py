biggest_y = 0
rock_formation = []


def bounds(x, y):
    return x <= 1000 and y <= biggest_y


def sign(num):
    return int(num / abs(num))


def make_rock_formation(line):
    arr = line.split(" -> ")
    for i in range(len(arr) - 1):
        start_x, start_y = arr[i].split(",")
        end_x, end_y = arr[i + 1].split(",")
        start_x, start_y, end_x, end_y = int(start_x), int(start_y), int(end_x), int(end_y)
        if start_x == end_x:
            for y in range(start_y, end_y + sign(end_y - start_y), sign(end_y - start_y)):
                rock_formation[y][start_x] = "#"
        else:
            for x in range(start_x, end_x + sign(end_x - start_x), sign(end_x - start_x)):
                rock_formation[start_y][x] = "#"


file_name = "input.txt"
file_in = open(file_name)
for line in file_in:
    line = line[:len(line) - 1]
    arr = line.split(" -> ")
    for elem in arr:
        arr2 = elem.split(",")
        x = int(arr2[0])
        y = int(arr2[1])
        if y > biggest_y:
            biggest_y = y
biggest_y += 2

file_in = open(file_name)
for i in range(biggest_y):
    arr = ["."] * (1000 + 1)
    rock_formation.append(arr)
rock_formation.append(["#"] * (1000 + 1))
for line in file_in:
    make_rock_formation(line[:len(line) - 1])

grains_of_sand = 0
more_sand = True
while more_sand:
    sand_x, sand_y = 500, 0
    while True:
        if not bounds(sand_x, sand_y + 1):
            more_sand = False
            break
        elif rock_formation[sand_y + 1][sand_x] == ".":
            sand_y += 1
        elif not bounds(sand_x - 1, sand_y + 1):
            more_sand = False
            break
        elif rock_formation[sand_y + 1][sand_x - 1] == ".":
            sand_y += 1
            sand_x -= 1
        elif not bounds(sand_x + 1, sand_y + 1):
            more_sand = False
            break
        elif rock_formation[sand_y + 1][sand_x + 1] == ".":
            sand_y += 1
            sand_x += 1
        elif rock_formation[sand_y][sand_x] == "o":
            more_sand = False
            break
        else:
            grains_of_sand += 1
            rock_formation[sand_y][sand_x] = "o"
            break


print(grains_of_sand)
for rock in rock_formation:
    print(rock)
