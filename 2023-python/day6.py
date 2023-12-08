from helpers import *
import math


def part1():
  lines = read_lines('day6.txt')
  times = get_nums(lines[0])
  distances = get_nums(lines[1])
  result = 1
  for i, time in enumerate(times):
    num_successes = get_successes(distances[i], time)
    if (num_successes > 0):
      result *= num_successes
  print(result)

def part2():
  lines = read_lines('day6.txt')
  time = get_aggregate_num(lines[0])
  distance = get_aggregate_num(lines[1])
  print(get_successes(distance, time))

  
def get_successes(distance, time):
    middle_time = math.ceil(time / 2)
    current_time = middle_time
    num_successes = 0
    while (calculate_distance_for_hold(current_time, time) > distance and current_time > 0):
      num_successes += 1
      current_time -= 1
    if num_successes > 0:
      if (time % 2 == 0):
        num_successes = num_successes + (num_successes - 1)
      else:
        num_successes = (num_successes - 1) * 2
    return num_successes

def get_nums(line):
  all_nums = line.split(":")[1].strip()
  return [int(element) for element in all_nums.split()]

def get_aggregate_num(line):
  all_nums = line.split(":")[1].strip().split()
  time = ''
  for num in all_nums:
    time += num
  return int(time)

def calculate_distance_for_hold(hold_time, max_time):
  return (max_time - hold_time) * hold_time

part2()