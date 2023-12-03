import os

def read_lines(file_name):
  file_path = os.path.join(os.path.dirname(__file__), 'inputs', file_name)
  print(file_path)
  file = open(file_path, 'r')
  data = file.readlines()
  return data