file = open("input.txt").read()

for end_index in range(4, len(file)):
    current4 = set(file[end_index - 4:end_index])
    if len(current4) == 4:
        break

print(end_index)
