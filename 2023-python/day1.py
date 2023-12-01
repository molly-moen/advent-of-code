import os

file_path = os.path.join(os.path.dirname(__file__), 'inputs', 'day1.txt')
print(file_path)
file = open(file_path, 'r')
data = file.readlines()

# Part 1
sum = 0
available_digits = {
  "1": 1,
  "2": 2,
  "3": 3,
  "4": 4,
  "5": 5,
  "6": 6,
  "7": 7,
  "8": 8,
  "9": 9,
  "one": 1,
  "two": 2,
  "three": 3,
  "four": 4,
  "five": 5,
  "six": 6,
  "seven": 7,
  "eight": 8,
  "nine": 9
}

def part1(data):
  for line in data:
    first_num = None
    last_num = None
    for character in line:
      if character.isdigit():
        if first_num == None:
          first_num = int(character) * 10
        last_num = int(character)
    line_value = first_num + last_num
    print('line_value is ' + str(line_value))
    sum += line_value
  print(sum)

def part2(data):
  sum = 0
  for line in data:
    first_num = None
    last_num = None
    lowest_index = len(line)
    print(lowest_index)
    highest_index = -1
    for potential_digit in available_digits:
      first_index = line.find(potential_digit)
      last_index = line.rfind(potential_digit)
      if (first_index >= 0):
        if (first_index < lowest_index):
          first_num = available_digits[potential_digit] * 10
          lowest_index = first_index
        if (last_index > highest_index):
          last_num = available_digits[potential_digit]
          highest_index = last_index
    
    line_value = first_num + last_num
    print('line_value is ' + str(line_value))
    sum += line_value
  print(sum)

part2(data)