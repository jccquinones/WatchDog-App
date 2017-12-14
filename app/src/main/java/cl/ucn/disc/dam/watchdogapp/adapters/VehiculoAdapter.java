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
import cl.ucn.disc.dam.watchdogapp.model.Vehiculo;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Jose Diaz, John Quiñonez
 */
@Slf4j
public final class VehiculoAdapter extends BaseAdapter {

    /**
     * Listado de Vehiculo
     */
    private final List<Vehiculo> vehiculos = new ArrayList<>();

    /**
     * Context
     */
    private final Context context;

    /**
     * @param context
     */
    public VehiculoAdapter(final Context context) {
        this.context = context;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return vehiculos.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Vehiculo getItem(int position) {
        return vehiculos.get(position);
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
            view = LayoutInflater.from(context).inflate(R.layout.row_vehiculo, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Vehiculo vehiculo = this.getItem(position);
        if (vehiculo != null) {
            int anio1 = vehiculo.getAnio();
            String anioStr = String.valueOf(anio1);

            viewHolder.patente.setText(vehiculo.getPatente());
            viewHolder.modelo.setText(vehiculo.getModelo());
            viewHolder.anio.setText(anioStr);
            viewHolder.marca.setText(vehiculo.getMarca());
            viewHolder.color.setText(vehiculo.getColor());
            viewHolder.descripcion.setText(vehiculo.getDescripcion());

        }

        return view;
    }

    /**
     * Agrega un listado de articulos al {@link List} de {@link Vehiculo}.
     *
     * @param vehiculos
     * @return VehiculoAdapter
     */
    public void addAll(final List<Vehiculo> vehiculos) {

        boolean changed = false;

        // Agrego los articulos
        if (vehiculos != null) {
            log.debug("Adding vehiculos: {}", vehiculos.size());
            changed = this.vehiculos.addAll(vehiculos);
            log.debug("Added {} vehiculos.", vehiculos.size());
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
        TextView modelo;
        TextView anio;
        TextView marca;
        TextView color;
        TextView descripcion;

        ViewHolder(final View view) {
            this.patente = view.findViewById(R.id.ra_patente);
            this.modelo = view.findViewById(R.id.ra_modelo);
            this.anio = view.findViewById(R.id.ra_anio);
            this.marca = view.findViewById(R.id.ra_marca);
            this.color = view.findViewById(R.id.ra_color);
            this.descripcion = view.findViewById(R.id.ra_descripcion);
        }

    }
}
