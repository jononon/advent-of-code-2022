num_contained = 0

with open("day4/input.txt") as file_in:
    for line in file_in:
        range1, range2 = line.split(",")
        range1num1, range1num2 = range1.split("-")
        range2num1, range2num2 = range2.split("-")

        r1n1 = int(range1num1)
        r1n2 = int(range1num2)
        r2n1 = int(range2num1)
        r2n2 = int(range2num2)
        
        if r1n1 >= r2n1:
            if r1n2 <= r2n2:
                num_contained += 1
                continue
        if r2n1 >= r1n1:
            if r2n2 <= r1n2:
                num_contained += 1
            

print(num_contained)
