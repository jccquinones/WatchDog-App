package cl.ucn.disc.dam.watchdogapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cl.ucn.disc.dam.watchdogapp.R;
import cl.ucn.disc.dam.watchdogapp.model.RegistroIngreso;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Jose Diaz, John Quiñones
 */
@Slf4j
public final class RegistroIngresoAdapter extends BaseAdapter {

    /**
     * Listado de RegistroIngreso
     */
    private final List<RegistroIngreso> registros = new ArrayList<>();

    /**
     * Context
     */
    private final Context context;

    /**
     * @param context
     */
    public RegistroIngresoAdapter(final Context context) {
        this.context = context;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return registros.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public RegistroIngreso getItem(int position) {
        return registros.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        final View view;

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.row_registro, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final RegistroIngreso registro = this.getItem(position);
        if (registro != null) {

            viewHolder.porteria.setText(registro.getPorteria());
            viewHolder.patente.setText(registro.getPatenteVehiculo());
            viewHolder.fecha.setText(registro.getFecha().toString());
        }

        return view;
    }

    /**
     * Agrega un listado de registros al {@link List} de {@link RegistroIngreso}.
     *
     * @param registros
     * @return RegistroIngresoAdapter
     */
    public void addAll(final List<RegistroIngreso> registros) {

        boolean changed = false;

        // Agrego los articulos
        if (registros != null) {
            log.debug("Adding registros: {}", registros.size());
            changed = this.registros.addAll(registros);
            log.debug("Added {} registros.", registros.size());
        }

        // Si cambio la coleccion, se refresca.
        if (changed) {

            super.notifyDataSetChanged();
        }
    }

    /**
     * Viewholder pattern
     */
    private static class ViewHolder {

        TextView patente;
        TextView fecha;
        TextView porteria;

        ViewHolder(final View view) {
            this.patente = view.findViewById(R.id.ra_patente3);
            this.fecha = view.findViewById(R.id.ra_fecha);
            this.porteria = view.findViewById(R.id.ra_porteria);

        }

    }
}
