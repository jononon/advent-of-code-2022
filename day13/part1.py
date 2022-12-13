def compareElems (left, right):
    if type(left) == int and type (right) == int:
        if left == right:
            return None
        else:
            return left < right
    elif type(left) == list and type(right) == list:
        for i in range(len(left)):
            if i >= len(right):
                return False
            comparison = compareElems(left[i], right[i])
            if comparison != None:
                return comparison
        if len(left) == len(right):
            return None
        return True
    elif type(left) == list:
        return compareElems(left, [right])
    else:
        return compareElems([left], right)

with open("input.txt") as file_in:
    counter = 0
    index = 1
    line = file_in.readline()
    while True:
        if line:
            arr1 = eval(line)
            arr2 = eval(file_in.readline())
            file_in.readline()
            line = file_in.readline()

            if compareElems(arr1, arr2):
                counter += index
            index += 1
        else:
            break

    print(counter)
        
