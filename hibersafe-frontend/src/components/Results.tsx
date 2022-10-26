
interface Props {
  link: string;
  index: number;
  side: string;
}

export default function Results(props: Props){
  return (
    <a href={props.link}>Resultado {props.side}.{props.index}</a>
  )
}