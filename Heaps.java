import java.util.ArrayList;

class Heaps<T extends Comparable<T>>
{

    private ArrayList<T> list;
    Heaps()
    {
        list=new ArrayList<>();
    }
    //for swapping
    public void swap(int first,int second)
    {
        T temp=list.get(first);
        list.set(first, list.get(second));
        list.set(second, temp);
    }

    //to get parent's index of the node
    private int parent(int index)
    {
        return (index-1)/2; //0 based index
    }
    //to get left child index
    private int left(int index)
    {
        return index*2+1;
    }
     //to get right child index
    private int right(int index)
    {
        return index*2+2;
    }
    public void insert(T value)
    {
        list.add(value);
        //after adding we have to arrange the tree from down to up
        upheap(list.size()-1);
    }
    private void upheap(int index)
    {
        if(index==0)
        return ;

        int p=parent(index);
        if(list.get(index).compareTo(list.get(p))<0)
        {
            //parent is greater than child so swap(Min Heap)
            swap(index, p);
            upheap(p);
        }
    }
    public T remove() throws Exception
    {
        if(list.isEmpty())
        {
            throw new Exception("Removing from empty list");
        }
        T temp=list.get(0); //removing top element in list
        T last=list.remove(list.size()-1); //removing last element in last 
        //adding last element to top
        if(!list.isEmpty())
        {
            list.set(0, last);
            downheap(0); //checking that all the nodes below are greater than parent
        }
        return temp;
    }
    private void downheap(int index)
    {
        int min=index;
        int left=left(index);
        int right=right(index);

        //checking left or right is small than the min
        if(left<list.size() && list.get(min).compareTo(list.get(left))>0)
        {
            min=left;
        }
         if(right<list.size() && list.get(min).compareTo(list.get(right))>0)
        {
            min=right;
        }
        //if we found smaller than min then swap 
        if(min!=index)
        {
            swap(min, index);
            downheap(min);
        }

    }


    public ArrayList<T> heapSort() throws Exception {
        ArrayList<T> data = new ArrayList<>();
        while(!list.isEmpty()) {
          data.add(this.remove());
        }
        return data;
      }

}