##This is a tool written for an assignment to analyze data given by NASA. 
##Basically, it tells the user with the longest gap in activity via XML in Ruby
##Written by Alexander Peck - For CS-208 - Knox College


# coding: utf-8
text = '/home/courses/cs208/web_log'


timelog = {} #Using a hash is actually appropriate this time, since we can disregard  dupes
timelog2 = {}
count = 0
usertime = 0   #The rest of these probably aren't needed, but just to be safe they will stay
File.foreach(text) do |line|
  line =~ /(.*) - - \[.*:(.*):(.*):(.*) -0400/
  user = $1
  hour = $2.to_i
  min = $3.to_i                                #Extracting desired values from the txt
  sec = $4.to_i
  usertime = (hour * 3600) + (min * 60) + sec #Gathering variable data though the text processing = efficiency

  if timelog[user] == nil     #Check if the user has even been recognized
    timelog[user] = usertime  #If not, give them a hash
  else
    timelog2[user] = usertime #Otherwise, they already have been recognized, and we need to record their updated times separately
  end                         #Having it in the second hash will allow the first time of activity to stay recorded, while updating up until the last in the 2nd hash
end

longest = 0                   
longhost = ''                #Variables for values and names of desired hash entries
timelog.each do |key, value| 
  count = count + 1          #Count how many different systems have been logged
end
timelog2.each do |key, value|
  check = (timelog2[key] - timelog[key])  #Check difference of time1 and 2 for every given key
  if longest < check    #Start finding the greatest difference
    longest = check     
    longhost = key      #Set the value and the name together
  end
end
################## Done
puts ""
puts "Number of distinct systems: " + count.to_s
puts "User with the longest gap of activity: " + longhost
puts ""
puts "The following numbers are presented in seconds. This value is the exact second of the day for each occurance."
puts ""
puts longhost + "'s first activity: " + timelog[longhost].to_s + ", and its last activity: " + timelog2[longhost].to_s
puts "For a difference of " + longest.to_s + " seconds."
puts ""
