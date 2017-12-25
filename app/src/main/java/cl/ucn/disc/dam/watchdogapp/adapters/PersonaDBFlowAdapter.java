package cl.ucn.disc.dam.watchdogapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.sql.language.OrderBy;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Locale;

import cl.ucn.disc.dam.watchdogapp.R;
import cl.ucn.disc.dam.watchdogapp.model.Persona;
import cl.ucn.disc.dam.watchdogapp.model.Persona_Table;
import cl.ucn.disc.dam.watchdogapp.model.Vehiculo;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author JOHN on 07-12-2017.
 */
@Slf4j
public final class PersonaDBFlowAdapter extends BaseAdapter {

    /**
     * FlowDB
     */
    private final FlowCursorList<Persona> flowCursorList;

    /**
     * Context
     */
    private final Context context;

    /**
     * OnClick over image
     */
    private final View.OnClickListener onClickListener;

    /**
     * @param context to get the {@link LayoutInflater}.
     */
    public PersonaDBFlowAdapter(@NonNull final Context context) {

        this.context = context;

        // SQL to get data
        this.flowCursorList = new FlowCursorList.Builder<>(
                SQLite.select()
                        .from(Persona.class)
                        .orderBy(OrderBy.fromProperty(Persona_Table.rut))
        ).build();

        // Data in the backend
        log.debug("Size: {}", this.flowCursorList.getCount());

        // Listener onClickImage
        this.onClickListener = v -> {
            final String url = (String) v.getTag();
            log.debug("Using url: {}", url);
        };

    }

    /**
     * Notifies the attached observers that the underlying data has been changed
     * and any View reflecting the data set should refresh itself.
     */
    @Override
    public void notifyDataSetChanged() {

        // Fresh the SQL
        this.flowCursorList.refresh();

        log.debug("Size: {}", this.flowCursorList.getCount());

        // Notify the list
        super.notifyDataSetChanged();

    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return (int) flowCursorList.getCount();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Persona getItem(int position) {
        return flowCursorList.getItem(position);
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
            view = LayoutInflater.from(context).inflate(R.layout.row_dueno, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);


        } else {
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Persona persona = this.getItem(position);
        if (persona != null) {
            int rut1 = persona.getRut();
            String rutStr = String.valueOf(rut1);
            int fono1 = persona.getTelefono();
            String fonoStr = String.valueOf(fono1);
            int anex1 = persona.getNumeroAnexo();
            String anexStr = String.valueOf(anex1);

            viewHolder.rut.setText(rutStr);
            viewHolder.nombre.setText(persona.getNombre());
            viewHolder.correo.setText(persona.getCorreoElectronico());
            viewHolder.fono.setText(fonoStr);
            viewHolder.anexo.setText(anexStr);
            viewHolder.localizacion.setText(persona.getLocalizacion());
            viewHolder.tipo.setText(persona.getTipo());
            viewHolder.cargo.setText(persona.getCargo());

        }

        return view;
    }

    /**
     * Viewholder pattern
     */
    @Slf4j
    private static class ViewHolder {

        TextView rut;
        TextView nombre;
        TextView correo;
        TextView fono;
        TextView anexo;
        TextView localizacion;
        TextView cargo;
        TextView tipo;

        ViewHolder(final View view) {
            this.rut = view.findViewById(R.id.ra_rut2);
            this.nombre = view.findViewById(R.id.ra_nombre2);
            this.correo = view.findViewById(R.id.ra_correo2);
            this.fono = view.findViewById(R.id.ra_telefono2);
            this.anexo = view.findViewById(R.id.ra_anexo2);
            this.localizacion = view.findViewById(R.id.ra_localizacion2);
            this.cargo = view.findViewById(R.id.ra_cargo2);
            this.tipo = view.findViewById(R.id.ra_tipo2);
        }

    }
}