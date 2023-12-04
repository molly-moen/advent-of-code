from helpers import *

def part1():
  lines = read_lines('day3.txt')
  nums = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
  period = '.'
  sum = 0
  for line_index, line in enumerate(lines):
    line = line.strip()
    current_index = 0
    while current_index < len(line):
      num = ''
      start_index = current_index
      if line[current_index].isdigit():
        num = line[current_index]
        current_index += 1
        while current_index < len(line) and line[current_index].isdigit():
          num += line[current_index]
          current_index += 1
        num = int(num)
        print('found num ' + str(num))
        end_index = current_index
        if has_adjacent_symbol(lines, line, line_index, start_index, end_index):
          # print('had adjacent symbol')
          sum += num
      else:
        current_index += 1
  print(sum)
      
def has_adjacent_symbol(lines, line, line_index, start_index, end_index):
  #print('checking for start_index ' + str(start_index) + ' and end_index ' + str(end_index))
  if ((start_index - 1 >= 0 and line[start_index - 1] != '.') or 
      (end_index < len(line) and line[end_index] != '.')):
      #print('found symbol in line, previous was ' + line[start_index - 1] + ' next was ' + line[end_index])
      print('found symbol in line, start index was ' + str(start_index) + ' end index was ' + str(end_index))
      return True
  return (is_valid_adjacent_line(lines, line_index - 1, start_index, end_index)
    or is_valid_adjacent_line(lines, line_index + 1, start_index, end_index))



def is_valid_adjacent_line(lines, index_to_check, start_index, end_index):
  if (index_to_check < 0 or index_to_check >= len(lines)):
    return False
  line = lines[index_to_check]
  line = line.strip()
  for i in range(start_index - 1, end_index + 1):
    if i < 0 or i >= len(line):
      continue
    character = line[i]
    if (not character.isdigit() and character != '.'):
      print('found adjacent line ' + str(index_to_check) + ' at index ' + str(i))
      return True
  return False

part1()