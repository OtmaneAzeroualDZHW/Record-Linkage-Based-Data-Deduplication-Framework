public class TransitiveClustering extends AbstractConfigurable
implements Clustering {

public Collection<XTuple> cluster(TupleSearcher searcher, TuplePair
pair) {
if (this.finished.contains(pair.getValue1().getTid())) {
log.debug("XTuple " + pair.getValue1().getTid() + " allready
processed, skip it");
return null;
 }
final Queue<XTuple> queue = new LinkedList<XTuple>();
final Set<XTuple> duplicates = new HashSet<XTuple>();

queue.add(pair.getValue1());
XTuple t1 = null;
while ((t1 = queue.poll()) != null) {
if (!this.finished.contains(t1.getTid())) {
final List<TuplePair> pairs = searcher.
findPairsByMatchingTypeAndTID(
MatchingType.Match, t1.getTid());
log.debug("Pairs found for tid: " + t1.getTid() + "- Num: "+ pairs.size());
duplicates.add(t1);
this.finished.add(t1.getTid());
for (TuplePair tuplePair : pairs) {
final XTuple t2 = tuplePair.getNot(t1);
if (!duplicates.contains(t2)) {
log.debug("add to queue: " + t2.getTid()); 
queue.add(t2);
    }
  }
 }
}
log.debug("Duplicates: " + duplicates.size());
return duplicates;
 }
}
