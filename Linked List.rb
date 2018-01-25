class Node
  attr_accessor :val, :next

  def initialize(v, nxt=nil) 
    @val = v
    @next = nxt
  end
end

class LinkedList                                                         
  include Enumerable                                              
  
  def initialize
    @head = @tail = nil
  end

  def add(v)                                                                             
    if @head == nil then
      @head = @tail = Node.new(v)
    else
      @tail.next = Node.new(v)
      @tail = @tail.next
    end
  end
  
  def addToFront(v)
    if @head == nil then
      @head = Node.new(v)
    end
      curr = Node.new(v)
      curr.next = @head
      @head = curr
  end
 

  def remove(val)
    curr = @head
    if curr.val == nil
      puts "The list is empty."
    elsif curr.val == val
      @head = curr.next
    else
      while (curr.val != val && curr.next != nil)
        trail = curr
        curr = curr.next
      end
      if curr.val == val
        trail.next = curr.next
      else
        puts "This value does not exist"
      end
    end
  end

  def removeLast
    curr = @head
    check = @head
    while curr.next != nil
      check = curr
      curr = curr.next
    end
    check.next = curr.next
    @tail = check
  end

  def addAfter(val, target)
    curr = @head
    check = @head
    temp = Node.new(val)
    while curr != nil
      if curr.val == target
        temp.next = check.next
        check.next = temp
      end
      check = curr
      curr = curr.next
    end
  end

  def each(&block)                                                             
    curr = @head
    while curr != nil
      block.call curr.val
      curr = curr.next
    end
  end

  def length
    count = 1
    curr = @head
    if curr == nil
      return 0
    end
    while curr.next != nil
      curr = curr.next
      count = count + 1
    end
    return count
  end

  def contains(val)
    curr = @head
    while (curr != nil)
      if curr.val == val
        return true
      end
      curr = curr.next
    end
    return false
  end

  def contains2(val)
    return self.any? {|i| i == val}
  end

  def frontLoaded?
    return self.all?{|i| i <= @head.val}
  end

  def num(val)
    temp = self.select{|v| v == val}
    return temp.length
  end
  
end


list = LinkedList.new

list.add(1)
list.add(2)
list.add(3)
puts "Initial 3 list:"
list.each{|v| puts v}
puts "With updated toFront method and additions"
list.add(4)
list.add(5)
list.add(6)
list.addToFront(10000)
list.each{|v| puts v}
puts "Next value should be 7"
puts list.length
puts "Now to remove a value"
list.remove(6)
puts "6 should now be gone"
list.each{|v| puts v}
puts 'To verify, length = ' + list.length.to_s
puts 'Can we remove from the front? Examine:'
list.remove(10000)
list.add(6)
list.each{|v| puts v}
puts 'Now we will test contains, checking if the list contains 5'
puts list.contains(5)
puts 'end of stupid stuff'
puts ''
puts 'testing exam methods'
puts 'Can we add between 5 & 6?'
list.addAfter(98765, 5)
list.each{|v| puts v}
puts 'Can we remove the last value?'
list.removeLast
list.each{|v| puts v}
list.removeLast
list.removeLast
list.removeLast
list.removeLast
list.removeLast
puts "We have now deleted the list, we will make another:"
list.add(6)
list.add(2)
list.add(1)
list.add(3)
list.add(6)
list.each{|v| puts v}
puts "Let's test frontloaded?"
puts "Does it work for this list?"
puts list.frontLoaded?
list.add(6)
puts "Now testing num, next line should be 3"
puts list.num(6)
