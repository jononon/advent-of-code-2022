'''
# AX AY AZ  3  6   0
# BX BY BZ  0  3   6
# CX CY CZ  6  0   3

# Rock- A or X - 1
# Paper - B or Y - 2
# Scissors - C or Z - 3

X += 1
Y += 2
Z += 3

X = LOSE = 0
Y = DRAW = 3
Z = WIN = 6

'''

scores = {
    "A X": 0 + 3,
    "A Y": 3 + 1,
    "A Z": 6 + 2,
    "B X": 0 + 1,
    "B Y": 3 + 2,
    "B Z": 6 + 3,
    "C X": 0 + 2,
    "C Y": 3 + 3,
    "C Z": 6 + 1,
}

def rps():
    total_score = 0

    with open("day2/input.txt") as file_in:
        for line in file_in:
            total_score += scores[line[:3]]

    return total_score

print(rps())
