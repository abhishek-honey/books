package net.jcip.examples;

import net.jcip.examples.ch3.Holder;

/**
 * StuffIntoPublic
 * <p/>
 * Unsafe publication
 *
 * @author Brian Goetz and Tim Peierls
 */
public class StuffIntoPublic {
    public Holder holder;

    public void initialize() {
        holder = new Holder(42);
    }
}
