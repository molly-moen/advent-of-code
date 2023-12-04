from helpers import *
import math
import numpy

def part1():
  lines = read_lines('day4.txt')
  sum = 0
  for line in lines:
    count = match_count(line)
    if count == 1:
      sum += 1
    elif count > 1:
      sum += int(math.pow(2, count - 1))
  print(sum)
   
def part2():
  lines = read_lines('day4.txt')
  counts = numpy.full(len(lines), 1)
  for line_index, line in enumerate(lines):
    matches = match_count(line)
    if matches > 0:
      for i in range(1, matches + 1):
        if (line_index + i < len(counts)):
          counts[line_index + i] += counts[line_index]
  print(numpy.sum(counts))



def match_count(line):
  line = line.strip()
  all_nums = line.split(":")
  nums = all_nums[1].split("|")
  winners = convert_input_to_sorted_array(nums[0])
  have = convert_input_to_sorted_array(nums[1])
  intersection = list(set(winners) & set(have))
  return len(intersection)


def convert_input_to_sorted_array(input):
    result = [int(element) for element in input.split()]
    result.sort()
    return result


part2()