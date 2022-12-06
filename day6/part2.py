file = open("input.txt").read()

for end_index in range(14, len(file)):
    current4 = set(file[end_index - 14:end_index])
    if len(current4) == 14:
        break

print(end_index)
