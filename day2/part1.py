'''
# AX AY AZ  3  6   0
# BX BY BZ  0  3   6
# CX CY CZ  6  0   3

# Rock- A or X
# Paper - B or Y
# Scissors - C or Z

X += 1
Y += 2
Z += 3

'''

scores = {
    "A X": 3 + 1,
    "A Y": 6 + 2,
    "A Z": 0 + 3,
    "B X": 0 + 1,
    "B Y": 3 + 2,
    "B Z": 6 + 3,
    "C X": 6 + 1,
    "C Y": 0 + 2,
    "C Z": 3 + 3,
}

def rps():
    total_score = 0

    with open("day2/input.txt") as file_in:
        for line in file_in:
            total_score += scores[line[:3]]

    return total_score

print(rps())